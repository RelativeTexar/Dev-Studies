import requests
import json
import time
import pandas as pd
import matplotlib.pyplot as plt


ABUSEIPDB_KEY = "db4b5168e8991fe01cec5c17aea343576bf108e0123c3bee58dacdfd90a3d430f885c55e990bf692"
VIRUSTOTAL_KEY = "300bc93e40a4398ac32cfb27a833e294cd94548dde5168d7f0908b1e8848a721"
IPQUALITYSCORE_KEY = "s2SIpaOSmAd9vLkjH4b1kr8La58fItvD"

def check_abuseipdb(ip):
    print(f"  Checking AbuseIPDB...", end="")
    url = "https://api.abuseipdb.com/api/v2/check"
    headers = {"Key": ABUSEIPDB_KEY, "Accept": "application/json"}
    params = {"ipAddress": ip, "maxAgeInDays": 90}
    
    try:
        response = requests.get(url, headers=headers, params=params)
        data = response.json()
        resultado = {
            "abuse_score": data["data"]["abuseConfidenceScore"],
            "total_reports": data["data"]["totalReports"],
            "country": data["data"]["countryCode"]
        }
        print(" OK")
        return resultado
    except Exception as e:
        print(f" ERRO: {e}")
        return {"abuse_score": 0, "total_reports": 0, "country": "N/A"}

def check_virustotal(ip):
    print(f"  Checking VirusTotal...", end="")
    url = f"https://www.virustotal.com/api/v3/ip_addresses/{ip}"
    headers = {"x-apikey": VIRUSTOTAL_KEY}
    
    try:
        response = requests.get(url, headers=headers)
        data = response.json()
        stats = data["data"]["attributes"]["last_analysis_stats"]
        resultado = {
            "malicious": stats["malicious"],
            "suspicious": stats["suspicious"],
            "harmless": stats["harmless"]
        }
        print(" OK")
        return resultado
    except Exception as e:
        print(f" ERRO: {e}")
        return {"malicious": 0, "suspicious": 0, "harmless": 0}

def check_ipqualityscore(ip):
    print(f"  Checking IPQualityScore...", end="")
    url = f"https://ipqualityscore.com/api/json/ip/{IPQUALITYSCORE_KEY}/{ip}"
    
    try:
        response = requests.get(url)
        data = response.json()
        resultado = {
            "fraud_score": data.get("fraud_score", 0),
            "is_proxy": data.get("proxy", False),
            "is_vpn": data.get("vpn", False),
            "country": data.get("country_code", "N/A")
        }
        print(" OK")
        return resultado
    except Exception as e:
        print(f" ERRO: {e}")
        return {"fraud_score": 0, "is_proxy": False, "is_vpn": False, "country": "N/A"}

def calculate_risk_score(abuse_result, vt_result, iqs_result):
    score = 0
    score += (abuse_result["abuse_score"] * 0.4)
    total = vt_result["malicious"] + vt_result["suspicious"] + vt_result["harmless"]
    if total > 0:
        malicious_percent = (vt_result["malicious"] / total) * 100
        score += (malicious_percent * 0.4)
    score += (iqs_result["fraud_score"] * 0.2)
    return min(int(score), 100)

def analyze_ip(ip):
    print(f"\n{'='*50}")
    print(f"Analisando: {ip}")
    print(f"{'='*50}")
    
    abuse_result = check_abuseipdb(ip)
    time.sleep(1)
    vt_result = check_virustotal(ip)
    time.sleep(1)
    iqs_result = check_ipqualityscore(ip)
    
    risk_score = calculate_risk_score(abuse_result, vt_result, iqs_result)
    
    if risk_score >= 70:
        status = "MALICIOUS"
    elif risk_score >= 40:
        status = "SUSPICIOUS"
    else:
        status = "CLEAN"
    
    print(f"\n{'-'*50}")
    print(f"RESULTADOS:")
    print(f"{'-'*50}")
    print(f"Status: {status}")
    print(f"Risk Score: {risk_score}/100")
    print(f"\nAbuseIPDB:")
    print(f"  Abuse Score: {abuse_result['abuse_score']}%")
    print(f"  Total Reports: {abuse_result['total_reports']}")
    print(f"  Pais: {abuse_result['country']}")
    print(f"\nVirusTotal:")
    print(f"  Maliciosos: {vt_result['malicious']}")
    print(f"  Suspeitos: {vt_result['suspicious']}")
    print(f"  Limpos: {vt_result['harmless']}")
    print(f"\nIPQualityScore:")
    print(f"  Fraud Score: {iqs_result['fraud_score']}/100")
    print(f"  Proxy/VPN: {'Sim' if iqs_result['is_proxy'] or iqs_result['is_vpn'] else 'Nao'}")
    print(f"  Pais: {iqs_result['country']}")
    print(f"{'='*50}\n")
    
    return {
        "ip": ip,
        "risk_score": risk_score,
        "status": status,
        "abuse_score": abuse_result["abuse_score"],
        "vt_malicious": vt_result["malicious"]
    }

def save_results(results_list):
    df = pd.DataFrame(results_list)
    df.to_csv("ip_analysis_results.csv", index=False)
    print("Resultados salvos: ip_analysis_results.csv")

def create_chart(results_list):
    ips = [r["ip"] for r in results_list]
    scores = [r["risk_score"] for r in results_list]
    
    colors = []
    for score in scores:
        if score >= 70:
            colors.append('#FF4444')
        elif score >= 40:
            colors.append('#FFAA00')
        else:
            colors.append('#44FF44')
    
    plt.figure(figsize=(10, 6))
    plt.barh(ips, scores, color=colors, alpha=0.8, edgecolor='black')
    plt.xlabel('Risk Score', fontsize=12, fontweight='bold')
    plt.title('IP Threat Analysis', fontsize=14, fontweight='bold')
    plt.xlim(0, 100)
    plt.axvline(x=40, color='orange', linestyle='--', alpha=0.5)
    plt.axvline(x=70, color='red', linestyle='--', alpha=0.5)
    plt.tight_layout()
    plt.savefig('ip_analysis_chart.png', dpi=300)
    print("Grafico salvo: ip_analysis_chart.png")

if __name__ == "__main__":
    print("="*50)
    print("   MALICIOUS IP HUNTER v1.0")
    print("="*50)
    
    ips_to_check = [
        "8.8.8.8",
        "1.1.1.1",
        "185.220.101.45",
    ]
    
    print(f"\nTotal de IPs: {len(ips_to_check)}\n")
    
    all_results = []
    
    for ip in ips_to_check:
        result = analyze_ip(ip)
        all_results.append(result)
        time.sleep(2)
    
    print("\nSalvando resultados...")
    save_results(all_results)
    create_chart(all_results)
    
    print("\nAnalise completa!")
    print(f"Maliciosos: {sum(1 for r in all_results if r['risk_score'] >= 70)}")
    print(f"Suspeitos: {sum(1 for r in all_results if 40 <= r['risk_score'] < 70)}")
    print(f"Limpos: {sum(1 for r in all_results if r['risk_score'] < 40)}")
    print("\nObrigado por usar IP Hunter!")
document.addEventListener('DOMContentLoaded', () => {
    const fileInput = document.getElementById('logFileInput');

    // Quando o usuário seleciona um arquivo, este evento é acionado.
    fileInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (!file) {
            return; // Sai da função se nenhum arquivo for selecionado.
        }

        // FileReader é a API do navegador para ler arquivos do computador do usuário.
        const reader = new FileReader();

        // Esta função é chamada QUANDO o arquivo for lido com sucesso.
        reader.onload = (e) => {
            const fileContent = e.target.result; // O conteúdo do arquivo como um grande texto.
            processLog(fileContent); // Chama nossa função principal para analisar o texto.
        };

        // Inicia a operação de leitura do arquivo.
        reader.readAsText(file);
    });

    function processLog(logData) {
        // 1. PARSING (Interpretação dos dados)
        // Divide o texto em linhas e remove linhas vazias.
        const lines = logData.split('\n').filter(line => line.trim() !== '');
        
        // Regex para extrair as partes de cada linha do log.
        // [Timestamp] IP METODO /recurso STATUS
        const logRegex = /\[(.*?)\]\s(.*?)\s(.*?)\s(.*?)\s(\d+)/;
        
        const parsedLogs = lines.map(line => {
            const match = line.match(logRegex);
            if (match) {
                return {
                    timestamp: match[1],
                    ip: match[2],
                    method: match[3],
                    resource: match[4],
                    status: parseInt(match[5], 10) // Converte o status para número
                };
            }
            return null; // Retorna nulo para linhas que não batem com o formato.
        }).filter(log => log !== null); // Remove as linhas nulas.

        // 2. ANALYSIS (Análise dos dados)
        let successCount = 0;
        let errorCount = 0;
        let failedLoginCount = 0;
        const ipSet = new Set(); // Set é perfeito para armazenar valores únicos.

        parsedLogs.forEach(log => {
            ipSet.add(log.ip); // Adiciona o IP ao Set. Duplicados são ignorados automaticamente.
            if (log.status >= 200 && log.status < 300) {
                successCount++;
            } else if (log.status >= 400) {
                errorCount++;
            }
            if (log.status === 401) { // 401 Unauthorized - tentativa de login falha
                failedLoginCount++;
            }
        });

        // 3. DISPLAY (Exibição dos resultados no HTML)
        updateDashboard(parsedLogs.length, successCount, errorCount, failedLoginCount);
        displayUniqueIps(Array.from(ipSet)); // Converte o Set para um Array para exibir.
        displayLogTable(parsedLogs);
    }

    function updateDashboard(total, success, error, failedLogins) {
        document.getElementById('totalRequests').textContent = total;
        document.getElementById('successRequests').textContent = success;
        document.getElementById('errorRequests').textContent = error;
        document.getElementById('failedLogins').textContent = failedLogins;
    }

    function displayUniqueIps(ips) {
        const listElement = document.getElementById('uniqueIps');
        listElement.innerHTML = ''; // Limpa a lista antes de adicionar novos itens.
        ips.forEach(ip => {
            const listItem = document.createElement('li');
            listItem.textContent = ip;
            listElement.appendChild(listItem);
        });
    }

    function displayLogTable(logs) {
        const tableBody = document.getElementById('logTableBody');
        tableBody.innerHTML = ''; // Limpa a tabela.

        logs.forEach(log => {
            const row = document.createElement('tr');
            
            // Adiciona uma classe especial se for uma linha de erro
            if (log.status >= 400) {
                row.classList.add('error-row');
            }

            row.innerHTML = `
                <td>${log.timestamp}</td>
                <td>${log.ip}</td>
                <td>${log.method}</td>
                <td>${log.resource}</td>
                <td>${log.status}</td>
            `;
            tableBody.appendChild(row);
        });
    }
});
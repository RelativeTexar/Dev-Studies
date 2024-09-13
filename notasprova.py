
nota1 = float(input("QUAL SUA PRIMEIRA NOTA: "))
nota2 = float(input("QUAL SUA SEGUNDA NOTA: "))
nota3 = float(input("QUAL SUA TERCEIRA NOTA: "))
nota4 = float(input("QUAL SUA QUARTA NOTA: "))


media = (nota1 + nota2 + nota3 + nota4) / 4

if media < 7:
    print(f"SUA MÉDIA DE {media} FOI MUITO BAIXA!!")
    nota_recuperacao = float(input("QUAL SUA NOTA DE RECUPERAÇÃO: "))
    media_final = (media + nota_recuperacao) / 2
    print(f"SUA MÉDIA FINAL FOI: {media_final:.1f}")
    
    if media_final < 6:
        print("REPROVADO")
    else:
        print("PARABÉNS, VOCÊ PASSOU!")
else:
    print(f"SUA MÉDIA DE {media:.1f} FOI SUFICIENTE, PARABÉNS!")

print("--- Fim da execução do programa! ---")

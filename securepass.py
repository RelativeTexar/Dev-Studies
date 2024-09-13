import random
import string

def gerar_senha(tamanho):
    caracteres = string.ascii_letters + string.digits + string.punctuation
    senha = ''
    
    for i in range(tamanho):
        senha += random.choice(caracteres)
    
    return senha

def main():
    print("Bem-vindo ao Gerador de Senhas Simples!")

    tamanho = int(input("Digite o tamanho desejado para a senha: "))

    senha = gerar_senha(tamanho)

    print(f"Sua senha gerada é: {senha}")

    salvar = input("Deseja salvar a senha em um arquivo? (S/N): ").upper()

    if salvar == 'S':
        with open('senha_gerada.txt', 'a') as arquivo:
            arquivo.write(f"Senha: {senha}\n")
        print("Senha salva no arquivo 'senha_gerada.txt'.")

main()

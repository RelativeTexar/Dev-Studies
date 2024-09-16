# Write code below
nome = str(input('Olá usuário, qual o seu nome?'))
print('olá, seu nome é', nome)

tamanho_nome = len(nome)
print(f'seu nome tem {tamanho_nome} letras.')
print('...')
print('você gosta do seu nome?')
pergunta = input('escreva S para gosto, N para não gosto')

if pergunta == 'S':
    print('que bom que você gosta')
elif pergunta == 'N':
    print('gostaria de mudar?')
    resposta2 = input('escreva S para sim, N para não')

  
    if resposta2 == 'S':
        novonome = input('qual nome você quer?')
        print(f'olá, {novonome}')
    else: 
        print('blz, fica com esse nome mesmo!')
else: 
    print('fechando o programa')

     

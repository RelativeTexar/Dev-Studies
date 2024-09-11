
gryff = 0 
raven = 0 
huffle = 0 
sly = 0 
#primeira pergunta
print('Q1) Do you like Dawn or Dusk?')
print('1) Dawn')
print('2) Dusk')
resposta = int(input('qual a sua resposta? 1 ou 2'))
if resposta == 1:
  gryff += 1
  raven += 1
elif resposta == 2:
  huffle += 1
  raven += 1 
else: 
  print('wrong input')
#segunda pergunta
print('Q2) When im dead, i want people to remember me as:')
print('1) The good')
print('2)The great')
print('3) The wise')
print('4) The bold')
resposta2 = int(input('Qual a sua resposta? 1 ou 2'))
#comandos if e else para decisão 
if resposta2 == 1:
  huffle += 2
elif resposta2 == 2:
  sly += 2
elif resposta2 == 3: 
  raven += 2
elif resposta2 == 4:
  gryff += 2
else:
  print('wrong input')
#terceira pergunta
print('Q3) which kind of instrument most pleases your ear?')
print('1) the violin')
print('2) the trumpet')
print('3) the piano')
print('4) the drum')
resposta3 = int(input('Qual sua resposta? 1 ou 2'))
# comandos if para decisão
if resposta3 == 1:
  sly += 4
elif resposta3 == 2:
  huffle += 4
elif resposta3 == 3:
  raven += 4 
elif resposta3 == 4:
  gryff += 4
else:
  print('wrong input')
#print com a casa com mais pontos
print('Gryffindor:', gryff)
print('Ravenclaw:', raven)
print('Hufflepuff', huffle)
print('Slytherin', sly)
print('Veja os resultados e veja qual foi a casa com mais pontos')



import random 

f1 = 'Dont just think. Act!'
f2 = 'All things are difficult before they are easy.'
f3 = 'Someone in your life needs a letter from you.'
f4 = 'Dont just think. Act!'
f5 = 'Your heart will skip a beat.'
f6 = 'The fortune you search for is in another cookie.'

frases = [f1, f2, f3, f4, f5, f6]
num = random.choice(frases)

print('voce recebeu um biscoito da sorte, quer abrir para ver a mensagem?')
abrir = input('digite S para abrir ou digite N para não abrir')
if abrir == 'S':
  print(num)
else: 
  print('certo, guarde para quando precisar!')
  



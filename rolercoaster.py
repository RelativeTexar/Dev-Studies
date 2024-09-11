h = int(input('qual sua altura?')) 

c = int(input('quantos creditos voce tem?')) 

if h > 137 and c > 10: 

  print('aproveite a atração') 

elif h < 137 and c > 10: 

  print('Altura não é suficiente para a atração') 

elif h > 137 and c < 10: 

  print('Credistos são insuficientes') 

else:  

  print('Sem creditos suficientes e sem altura sufuciente') 

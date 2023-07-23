import requests 
from bs4 import BeautifulSoup
import csv

resultados = []

def get_resultados_from_url(url):
  r = requests.get(url)
  soup = BeautifulSoup(r.text, 'html.parser')

  main_div = soup.find('div', class_='dataResultado')

  resultados = []

  for div in main_div.find_all('div', {'class': 'data branco'}):
    in_divs = div.find_all('div')
    data_turno = {
      'data': in_divs[1].text.strip().split(' ')[-1],
      'turno': in_divs[0].text.strip().split(' ')[-1],
    }
    resultados.append(data_turno)
      
  numeros = [d.text.strip() for d in main_div.find_all('div', {'class': 'numeros'})]
  
  rix = 0
  pix = 0
  
  for dt in resultados:
    for i, n in enumerate(numeros[pix : pix + 10]):
      dt['premio{}'.format(i + 1)] = n
    pix = pix + 10
    
  return resultados

for i in range(51):
  url = f'https://lotece.com.br/resultados/?pg={i}&data='
  
  print(f'Iniciando: {url}')
  
  url_resultados = get_resultados_from_url(url)
  
  resultados.extend(url_resultados)
  
  print('Concluído')

columns = ['data', 'turno'] + [f'premio{i}' for i in range(1, 11)]

with open('lotece.csv', 'w') as f:
  w = csv.DictWriter(f, columns)
  w.writeheader()
  w.writerows(resultados)
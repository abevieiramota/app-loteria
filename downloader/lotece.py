import requests 
from bs4 import BeautifulSoup
import csv

resultados = []

def get_resultados_from_url(url):
  r = requests.get(url)
  soup = BeautifulSoup(r.text, 'html.parser')

  main_div = soup.find('div', class_='dataResultado')

  resultados = []
  current_resultado = {}
  current_premio = None

  for div in main_div.find_all('div'):
    if div.get('class') == ['data', 'branco']:
      div_parts = div.text.split(' ')

      current_resultado = {
        'data': div_parts[1],
        'turno': div_parts[-1],
      }
      current_premio = 0
      resultados.append(current_resultado)
    elif div.get('class') == ['numeros']:
      current_premio += 1
      key = f'premio{current_premio}'
      current_resultado[key] = div.text.strip()

  resultados.append(current_resultado)

  return resultados

for i in range(51):
  url = f'http://www.lotece.com.br/v2/?page_id=70&pg={i}&data='
  
  print(f'Iniciando: {url}')
  
  url_resultados = get_resultados_from_url(url)
  
  resultados.extend(url_resultados)
  
  print('Concluído')

columns = ['data', 'turno'] + [f'premio{i}' for i in range(1, 11)]

with open('lotece.csv', 'w') as f:
  w = csv.DictWriter(f, columns)
  w.writeheader()
  w.writerows(resultados)
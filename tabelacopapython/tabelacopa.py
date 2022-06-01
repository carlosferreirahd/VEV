from os import listdir
from os.path import isfile, join
import json
import sys
from tabulate import tabulate

if __name__ == '__main__':
  # reading files names
  json_files = [f[:-5] for f in listdir('.') if isfile(join('.', f)) and f.endswith('.json')]
  
  # sorting by timestamp
  json_files.sort(key=lambda x:x)

  # get latest
  latest_file_name = json_files.pop()+'.json'

  try:
    f = open(latest_file_name)

    data = json.load(f)

    f.close()

    table_headers = ['Time', 'PTS', 'PJ', 'VIT', 'E', 'DER', 'GP', 'GC', 'SG']

    for grupo in data:
      print(f"Grupo {grupo['grupo']}")
      table = []
      table.append(table_headers)

      for time in grupo['times']:
        time_name = time['time']
        pts = time['pontos']
        pj = time['partidas_jogadas']
        vit = time['vitorias']
        e = time['empates']
        gp = time['gols_pro']
        gc = time['gols_contra']
        sg = time['saldo_de_gols']

        table_line = [time_name, pts, pj, vit, e, gp, gc, sg]
        table.append(table_line)
      
      print(tabulate(table, headers='firstrow', tablefmt='fancy_grid'))
      print()

  except:
    print('Erro ao let/abrir arquivo')
    sys.exit()

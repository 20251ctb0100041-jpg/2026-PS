# debug_teste/01b-debug.py
# ATENÇÃO: 4 erros propositais. Encontre e corrija todos!
# Rode de dentro de 05_modulos/: python debug_teste/01b-debug.py

from conversores import Temperatura # o erro estava no import direto

from conversores import celsius_para_kelvin # o erro estava em 'converter distância'
resultado = celsius_para_kelvin(25)
print(f"25°C em K:{resultado}")

from utils import formatar_resultado #  erro estava no caminho 'utils formatador'
print(formatar_resultado("teste", 100, "km", 62.1, "mi")) # o erro estava no excesso de argumentos

from conversores import km_para_milhas
print(f"50 km = {km_para_milhas(50):.2f} mi")

from debug_teste import algo
# valida a entrada pra nao quebrar o programa
def validar_numero(valor_str, minimo=None, maximo=None):
    """Valida se uma string é um númeroe está dentro dos limites."""
    try:
        valor = float(valor_str)
        # travas de limite
        if minimo is not None and valor < minimo:
            return False, f"Valor abaixo do minimo ({minimo})"
        if maximo is not None and valor > maximo:
            return False, f"Valor acima do maximo ({maximo})"
        return True, valor
    except ValueError:
        return False, "Invalido, digite um número"
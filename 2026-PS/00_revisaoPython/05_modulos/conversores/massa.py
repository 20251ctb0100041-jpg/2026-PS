"""Módulo para conversão de massas."""

# conversao de peso
def kg_para_libras(kg):
    """Converte kg para libras."""
    return kg * 2.20462

def kg_para_gramas(kg):
    """Converte kg para gramas."""
    return kg * 1000

def libras_para_kg(libras):
    """Converte libras para kg."""
    return libras / 2.20462

if __name__ == "__main__":
    # testes
    print(f"Teste 1kg: {kg_para_gramas(1)}g")
    print(f"Teste 1kg: {kg_para_libras(1)}lb")
    print(f"Teste 1lb: {libras_para_kg(1)}kg")
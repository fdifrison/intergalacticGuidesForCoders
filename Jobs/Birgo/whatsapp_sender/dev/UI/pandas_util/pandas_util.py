import pandas as pd


def read_number(path: str):
    df = pd.read_excel(path)
    numbers = validate_df(df)
    return numbers


def validate_df(df: pd.DataFrame) -> list:
    valid = df[df['inviato'] == 0]  # if 'inviati' = 1 -> discard
    numbers: list(int) = valid['numeri'].values.tolist()
    for i, n in enumerate(numbers):
        if not str(n).startswith('+'):
            numbers[i] = '+39' + str(n)
    return numbers


if __name__ == '__main__':
    df = read_number('numeri_birgo.xlsx')

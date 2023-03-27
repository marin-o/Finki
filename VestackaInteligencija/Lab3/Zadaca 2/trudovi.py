from constraint import *



if __name__ == '__main__':
    num = int(input())

    papers = dict()

    paper_info = input()
    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        papers[title] = topic
        paper_info = input()

    # Tuka definirajte gi promenlivite
    # variables = list()
    # for key in papers.keys():
    #     variables.append(key + " (" + papers[key] + ")")
    variables = papers
    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())

    # Dokolku vi e potrebno moze da go promenite delot za dodavanje na promenlivite
    problem.addVariables(variables, domain)

    # Tuka dodadete gi ogranichuvanjata
    result = problem.getSolution()

    # Tuka dodadete go kodot za pechatenje
    print(result)

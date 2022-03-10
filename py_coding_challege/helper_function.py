
def read_stdin():
    '''
    Read stdin and return a list.
    Assumption: all elements are integers
    If more than one element in readline return a list of integers.
    '''
    run = True
    import sys
    buffer : list = []
    while run:
        line = sys.stdin.readline().rstrip('\n')
        if not line:
            run = False
        else:
            if len(line) == 1:
                buffer.append(int(line))
            else:
                line = [int(i) for i in line.split()]
                buffer.append(line)
            
    return buffer

buffer = read_stdin()
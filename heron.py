# this file utilized heron of alexandira's algorithm

def heron(x):
    g = x/10
    current = 0.5*(g + (x/g)) 
    prev = 0.00
    while abs(prev - current) > 0.00001 : 
        prev = current  
        current = 0.5*(prev + (x/prev)) 
    root = current
    print 'the square root of ' + str(x) + ' is ' + str(root)

heron(32)

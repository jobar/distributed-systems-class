from math import sqrt, pow
from datetime import datetime
from multiprocessing import Pool


def approxPi(itv, startIdx=0, endIdx=None):
    s = 0.0
    for k in range(startIdx + 1, (endIdx if endIdx else itv) + 1):
        x = k / float(itv)
        y = sqrt(1 - pow(x, 2))
        s += (1 / float(itv)) * y
    return 4 * s


def printRes(pow10, numProc, pi, dur):
    print('Approximation using 10^{0}'.format(pow10) +
          'intervals and {0} workers:'.format(numProc))
    print ('  Pi:  {0}'.format(pi))
    print ('  T:   {0}'.format(dur))
    print('-' * 60)


def printPi(pow10):
    itv = int(pow(10, pow10))
    t1 = datetime.now()
    pi = approxPi(itv)
    t2 = datetime.now()
    printRes(pow10, 1, pi, t2 - t1)


def worker((itv, startIdx, endIdx)):
    return approxPi(itv, startIdx=startIdx, endIdx=endIdx)


def printPiPar(pow10, numProc):
        itv = int(pow(10, pow10))
        t1 = datetime.now()
        pool = Pool(numProc)
        chunkSize = itv / numProc
        leftOver = itv % numProc
        chunks = [(itv, w * chunkSize,
                  (w + 1) * chunkSize + (
                      leftOver if w == numProc - 1 else 0))
                  for w in range(0, numProc)]
        results = pool.map(worker, chunks)
        pi = sum(results)
        t2 = datetime.now()
        printRes(pow10, numProc, pi, t2 - t1)


for pow10 in range(1, 9):
    printPi(pow10)
    printPiPar(pow10, 2)
    printPiPar(pow10, 4)
    for i in range(0, 2):
        print('*' * 60)

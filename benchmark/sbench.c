#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <time.h>

void main(){
    int i = 0;
    clock_t start, end;
    printf("Benchmark running...\n");
    start = clock();
    while (i < 2147483647){
        sqrt(i);
        i++;
    }
    while (i >= 0){
        i--;
        sqrt(i);
    }
    end = clock();

    double time = ((double)end - start)/CLOCKS_PER_SEC;
    printf("You got a score of %f seconds. Press enter to exit. ", time);
    char* inp;
    fgets(inp, 2000, stdin);
}
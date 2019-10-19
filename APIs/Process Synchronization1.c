#include<stdio.h>
 // to find the minimum time for process synchronization 
int find(int time[], int n){
    int i, mini = time[0], position = 0;
 
    for(i = 1; i < n; ++i){
        if(time[i] < mini){
            mini = time[i];
            positon = i;
        }
    }
    
    return positon;
}
 
int main()
{
	//Asking the user to input the number of frames,required pages and String referred
    int f, p, frame[10], page[30], count = 0, time[10], flags1, flags2, i, j, pos,faults = 0;
    printf("Enter number of frames: ");
    scanf("%d", &f);
    
    printf("Enter number of pages: ");
    scanf("%d", &p);
    
    printf("Enter reference string: ");
    
    for(i = 0; i < p; ++i){
        scanf("%d", &page[i]);
    }
    
    for(i = 0; i < f; ++i){
        frame[i] = -1;
    }
    
    for(i = 0; i < p; ++i){
        flags1 = flags2 = 0;
        
        for(j = 0; j < f; ++j){
            if(frame[j] == page[i]){
                count++;
                time[j] = count;
                   flags1 = flags2 = 1;
                   break;
               }
        }
        
        if(flags1 == 0){
            for(j = 0; j < f; ++j){
                if(frame[j] == -1){
                    count++;
                    faults++;
                    frames[j] = pages[i];
                    time[j] = count;
                    flags2 = 1;
                    break;
                }
            }    
        }
        
        if(flag2 == 0){
            pos = findLRU(time, f);
            count++;
            faults++;
            frame[pos] = page[i];
            time[pos] = count;
        }
	// Nitish       
        printf("\n");
        
        for(j = 0; j < f; ++j){
            printf("%d\t", frame[j]);
        }
    }
    // here the faults are going to rectified 
    printf("\n\nTotal Page Faults = %d", faults);
    return 0;
}

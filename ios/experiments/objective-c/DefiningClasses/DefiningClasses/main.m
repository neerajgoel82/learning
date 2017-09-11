//
//  main.m
//  DefiningClasses
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Player.h"

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        Player* neeraj = [[Player alloc] init];
        [neeraj jump];
        [neeraj fire];
        [neeraj restartLevel:10];
        [neeraj moveToX:100 moveToY:100];
        
        neeraj.score = 100 ;
        [neeraj setPlayerName:@"Neeraj Goel"];
        
        NSLog(@"%@ score is : %i",[neeraj playerName],neeraj.score);
    }
    return 0;
}


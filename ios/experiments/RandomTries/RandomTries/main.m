//
//  main.m
//  RandomTries
//
//  Created by Neeraj Goel on 24/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Utilities.h"



int main(int argc, const char * argv[])
{

    @autoreleasepool {
        
        // insert code here...
        NSLog(@"Hello, World!");
        NSLog([Utilities cleanUpPhoneNumber:@"(989)-9867800"]);
        NSLog([Utilities cleanUpPhoneNumber:@"+91-(989)-9867800"]);
        
    }
    return 0;
}


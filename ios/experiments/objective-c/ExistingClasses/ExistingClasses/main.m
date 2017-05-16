//
//  main.m
//  ExistingClasses
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <Foundation/Foundation.h>

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        
        // insert code here...
        NSLog(@"Hello, World!");
        NSString *msg = @"This should be displayed in all caps";
        NSString *caps = [msg uppercaseString];
        NSLog(@"The result string is : %@", caps);
        
        NSDate* today = [NSDate date];
        NSLog(@"Today the date is : %@", today);
    }
    return 0;
}


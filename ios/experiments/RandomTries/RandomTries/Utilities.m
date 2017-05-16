//
//  Utilities.m
//  RandomTries
//
//  Created by Neeraj Goel on 24/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "Utilities.h"

@interface Utilities()
+(NSString*)getCountryCode;
@end

@implementation Utilities
+(NSString*)getCountryCode{
    return @"+91";
}

+(NSString*)cleanUpPhoneNumber:(NSString*)phone {
    NSMutableString* newNumber = [[NSMutableString alloc]init];
    for (NSInteger charIdx=0; charIdx< phone.length; charIdx++)
    {
        unichar element = [phone characterAtIndex:charIdx];
        if(charIdx == 0 && charIdx != '+') {
            [newNumber appendString:[Utilities getCountryCode]];
            if(element == '0')
                continue ;
        }
        if(!isdigit(element)) {
            continue ;
        }
        [newNumber appendString:[NSString stringWithFormat: @"%c",element]];
    }
    return newNumber ;
}

@end

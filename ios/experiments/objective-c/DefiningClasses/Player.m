//
//  Player.m
//  DefiningClasses
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "Player.h"

@implementation Player
-(void) jump{
    NSLog(@"INside jump");
}

-(void) fire{
    NSLog(@"Inside Fire");
}

-(void) restartLevel:(int)level{
    NSLog(@"Restart Level : %i", level);
}

-(void) moveToX:(int)x moveToY:(int)y{
    NSLog(@"Moved to x:%i y:%i", x, y);
}
@end

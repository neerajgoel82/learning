//
//  Player.h
//  DefiningClasses
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Player : NSObject

@property int score ;
@property (nonatomic) NSString* playerName ;
-(void) jump ;
-(void) fire ;
-(void) restartLevel:(int) level ;
-(void) moveToX:(int)x
        moveToY:(int)y ;
@end

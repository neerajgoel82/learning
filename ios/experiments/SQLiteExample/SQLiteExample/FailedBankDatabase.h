//
//  FailedBankDatabase.h
//  SQLiteExample
//
//  Created by Neeraj Goel on 14/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <sqlite3.h>
@interface FailedBankDatabase : NSObject {
    sqlite3 *_database;
}

+ (FailedBankDatabase*)database;
- (NSArray *)failedBankInfos;

@end

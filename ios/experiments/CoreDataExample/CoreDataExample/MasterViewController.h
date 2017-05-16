//
//  MasterViewController.h
//  CoreDataExample
//
//  Created by Neeraj Goel on 15/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MasterViewController : UITableViewController
@property (nonatomic,strong) NSManagedObjectContext* managedObjectContext;
@property (nonatomic, strong) NSArray *failedBankInfos;
@end

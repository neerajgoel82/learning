//
//  MasterViewController.h
//  SQLiteExample
//
//  Created by Neeraj Goel on 14/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <CoreData/CoreData.h>

@interface MasterViewController : UITableViewController <NSFetchedResultsControllerDelegate>

@property (strong, nonatomic) NSFetchedResultsController *fetchedResultsController;
@property (strong, nonatomic) NSManagedObjectContext *managedObjectContext;

@end

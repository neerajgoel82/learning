//
//  PhotoTableViewController.m
//  PhotoViewer
//
//  Created by Neeraj Goel on 08/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "PhotoTableViewController.h"
#import "Photo.h"
#import "DisplayViewController.h"

@interface PhotoTableViewController (){
    NSMutableArray* photos ;
}

@end

@implementation PhotoTableViewController

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
 
    [super viewDidLoad];
    
    photos = [[NSMutableArray alloc] init];
    
    Photo* pic = [[Photo alloc] init];
    pic.name = @"emeraldbay";
    pic.image = @"emeraldbay";
    pic.description = @"emeraldbay";
    [photos addObject:pic];
    
    
    pic = [[Photo alloc] init];
    pic.name = @"joshuatree";
    pic.image = @"joshuatree";
    pic.description = @"joshuatree";
    [photos addObject:pic];
    
    
    pic = [[Photo alloc] init];
    pic.name = @"redrock";
    pic.image = @"redrock";
    pic.description = @"redrock";
    [photos addObject:pic];
    
    
    pic = [[Photo alloc] init];
    pic.name = @"snowman";
    pic.image = @"snowman";
    pic.description = @"snowman";
    [photos addObject:pic];
    
    
    pic = [[Photo alloc] init];
    pic.name = @"sunset";
    pic.image = @"sunset";
    pic.description = @"sunset";
    [photos addObject:pic];
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return [photos count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"imageCell" forIndexPath:indexPath];
    
    Photo *currentPhoto = photos[indexPath.row] ;
    cell.textLabel.text = currentPhoto.name ;
    // Configure the cell...
    
    return cell;
}

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    DisplayViewController* display = [segue destinationViewController];
    
    NSIndexPath* indexPath = [self.tableView indexPathForSelectedRow];
    Photo* currentPhoto = photos[indexPath.row];
    
    // Pass the selected object to the new view controller.
    [display setCurrentPhoto:currentPhoto];
}


/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/



@end

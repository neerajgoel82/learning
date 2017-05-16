//
//  DetailViewController.h
//  iPadModalExample
//
//  Created by Neeraj Goel on 08/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController <UISplitViewControllerDelegate>

@property (strong, nonatomic) id detailItem;

@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@end

//
//  FlipsideViewController.h
//  UtilityAppExample
//
//  Created by Neeraj Goel on 07/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FlipsideViewController;

@protocol FlipsideViewControllerDelegate
- (void)flipsideViewControllerDidFinish:(FlipsideViewController *)controller;
@end

@interface FlipsideViewController : UIViewController

@property (weak, nonatomic) id <FlipsideViewControllerDelegate> delegate;

- (IBAction)done:(id)sender;

@end

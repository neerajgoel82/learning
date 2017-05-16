//
//  ViewController.m
//  DatePickerExample
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UIDatePicker *chosenDate;

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}
- (IBAction)showDay:(id)sender {
    NSDateFormatter* formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"EEEE"];
    NSString *dateString = [formatter stringFromDate:[self.chosenDate date]];
    
    NSString* msg = [NSString stringWithFormat:@"The day is %@",dateString];
    UIAlertView* dateAlert = [[UIAlertView alloc] initWithTitle:@"Day" message:msg delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
    [dateAlert show];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end

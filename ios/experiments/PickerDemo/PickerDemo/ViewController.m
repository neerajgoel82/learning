//
//  ViewController.m
//  PickerDemo
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property NSArray* moods ;
@end

@implementation ViewController

-(NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView{
    return 1 ;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    return [self.moods count];
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component{
    return self.moods[row];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    self.moods = @[@"Happy",@"Sad",@"Ecstatic",@"Angry" ];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component{
    UIAlertView* pickerAlert = [[UIAlertView alloc] initWithTitle:@"Picker Choice" message:self.moods[row] delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
    [pickerAlert show];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end

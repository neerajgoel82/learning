//
//  ViewController.m
//  BasicInteraction
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UILabel *labelName;
@property (weak, nonatomic) IBOutlet UITextField *txtName;
@end

@implementation ViewController

- (IBAction)changeLabel:(id)sender {
    NSString* name = [[self txtName] text];
    NSString* msg = [NSString stringWithFormat:@"Hello, %@",name];
    [[self labelName] setText:msg];
    [[self txtName] resignFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [textField resignFirstResponder];
    return YES;
}

-(void) touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event{
    [self.view endEditing:YES];
}
     
- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end

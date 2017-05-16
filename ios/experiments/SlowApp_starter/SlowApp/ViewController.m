//
//  ViewController.m
//  SlowApp
//
//  Created by Simon Allardice on 11/17/13.
//  Copyright (c) 2013 Simon Allardice. All rights reserved.
//

#import "ViewController.h"

@interface ViewController (){
    dispatch_queue_t myQueue ;
}
// one outlet for the label.
@property (weak, nonatomic) IBOutlet UILabel *resultLabel;

@end

@implementation ViewController


- (IBAction)buttonAction:(id)sender {
    
    if(!myQueue){
        myQueue = dispatch_queue_create("com.neerajgoel.gcdtets", NULL);
    }
    
    dispatch_async(myQueue, ^{[self longRunningOperation];});
}

-(void) longRunningOperation {
    [NSThread sleepForTimeInterval:5];
    dispatch_async(dispatch_get_main_queue(),^{[self.resultLabel setText:[NSString stringWithFormat:@"Results: %d", arc4random()]];});
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

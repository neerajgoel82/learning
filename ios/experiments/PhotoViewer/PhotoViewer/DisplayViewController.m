//
//  DisplayViewController.m
//  PhotoViewer
//
//  Created by Neeraj Goel on 08/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "DisplayViewController.h"
#import "DetailViewController.h"

@interface DisplayViewController ()
@property (weak, nonatomic) IBOutlet UIImageView *currentImage;

@end

@implementation DisplayViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
 
    UIImage* img = [UIImage imageNamed:self.currentPhoto.image];
    [self.currentImage setImage:img];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    DetailViewController* detail = [segue destinationViewController];
    
    // Pass the selected object to the new view controller.
    [detail setCurrentPhoto:self.currentPhoto];
}

@end

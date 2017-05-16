//
//  ViewController.m
//  TablesDemo
//
//  Created by Neeraj Goel on 06/06/14.
//  Copyright (c) 2014 Neeraj Goel. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController {
    NSDictionary* courseDetails ;
    NSArray* courseNames ;
    
    NSDictionary* webCourseDetails ;
    NSArray* webCourseNames ;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    NSURL* url = [[NSBundle mainBundle] URLForResource:@"courses" withExtension:@"plist"];
    courseDetails = [NSDictionary dictionaryWithContentsOfURL:url];
    courseNames = courseDetails.allKeys;

    NSURL* weburl = [[NSBundle mainBundle] URLForResource:@"courses_web" withExtension:@"plist"];
    webCourseDetails = [NSDictionary dictionaryWithContentsOfURL:weburl];
    webCourseNames = webCourseDetails.allKeys;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 2 ;
}

-(NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{
    if(section == 0)
        return @"iOS courses";
    else
        return @"Web courses";
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell* cell = [tableView dequeueReusableCellWithIdentifier:@"cell"];
    
    UIImage* cellImage = [UIImage imageNamed:@"TablesCellImage"];
    [cell.imageView setImage:cellImage];
    if(indexPath.section == 0){
        cell.textLabel.text = courseNames[indexPath.row];
        cell.detailTextLabel.text = courseDetails[courseNames[indexPath.row]];
    }
    else{
        cell.textLabel.text = webCourseNames[indexPath.row];
        cell.detailTextLabel.text = webCourseDetails[webCourseNames[indexPath.row]];
    }
    return cell ;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if(section == 0 ){
        return [courseNames count] ;
    }
    else{
        return [webCourseNames count];
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}

@end

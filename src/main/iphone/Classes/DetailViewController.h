//
//  DetailViewController.h
//  Wordvader
//
//  Created by Alex Winston on 12/8/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface DetailViewController : UIViewController<UINavigationControllerDelegate, UITableViewDelegate> {
	int solutionIndex;
	
	UITableView *wordsTableView;
	UISegmentedControl *wordsSegmentControl;
	
	NSMutableArray *segmentWordsSections;
	NSMutableArray *segmentWords;
}
@property (nonatomic, retain) IBOutlet UITableView *wordsTableView;
@property (nonatomic, retain) IBOutlet UISegmentedControl *wordsSegmentControl;

- (void)setSolutionIndex:(int)index;
- (IBAction)valueChanged:(id)sender;

@end

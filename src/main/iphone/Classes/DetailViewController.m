//
//  DetailViewController.m
//  Wordvader
//
//  Created by Alex Winston on 12/8/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "DetailViewController.h"


@implementation DetailViewController
@synthesize wordsTableView;
@synthesize wordsSegmentControl;


- (void)setSolutionIndex:(int)index {
	solutionIndex = index;
}

#pragma mark -
#pragma mark View lifecycle

- (void)viewDidLoad {
	//self.tableView.allowsSelection = NO;

	NSDictionary *solutionDictionary =
		[[[[NSUserDefaults standardUserDefaults] arrayForKey:@"AWWordvaderSolutions"] objectAtIndex:solutionIndex] objectForKey:@"AWWordvaderSolution"];

	// Insert the segments
	NSArray *segmentTitles = [solutionDictionary objectForKey:@"hintGroupSegments"];
	for (int i = 0; i < segmentTitles.count; i++) {
		[wordsSegmentControl setTitle:[segmentTitles objectAtIndex:i] forSegmentAtIndex:i];
	}
	// TODO Fix this little hack because removing and inserting doesn't work
	if (segmentTitles.count == 1)
		[wordsSegmentControl removeSegmentAtIndex:1 animated:NO];
	
	// Get the words from the solution
	segmentWordsSections = [solutionDictionary objectForKey:@"hintGroupKeys"];
	segmentWords = [solutionDictionary objectForKey:@"hintGroups"];
}


#pragma mark -
#pragma mark Table view data source

// Customize the number of sections in the table view.
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return [[[segmentWords objectAtIndex:wordsSegmentControl.selectedSegmentIndex] allKeys] count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
	return [[segmentWordsSections objectAtIndex:wordsSegmentControl.selectedSegmentIndex] objectAtIndex:section];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	NSArray *selectedWordsSections = [segmentWordsSections objectAtIndex:wordsSegmentControl.selectedSegmentIndex];
	NSDictionary *selectedWords = [segmentWords objectAtIndex:wordsSegmentControl.selectedSegmentIndex];
	
    return [[selectedWords objectForKey:[selectedWordsSections objectAtIndex:section]] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellIdentifier = @"Cell";
    
	UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier] autorelease];
    }
    
	NSArray *selectedWordsSections = [segmentWordsSections objectAtIndex:wordsSegmentControl.selectedSegmentIndex];
	NSDictionary *selectedWords = [segmentWords objectAtIndex:wordsSegmentControl.selectedSegmentIndex];
	
	// Configure the cell.
	cell.selectionStyle = UITableViewCellSelectionStyleNone;
	cell.textLabel.text = [[selectedWords objectForKey:[selectedWordsSections objectAtIndex:indexPath.section]] objectAtIndex:indexPath.row];
	
    return cell;
}


#pragma mark -
#pragma mark Table view delegate



#pragma mark -
#pragma mark Segmented control delegate


- (IBAction)valueChanged:(id)sender {
	UISegmentedControl *segmentedControl = (UISegmentedControl*) sender;
	switch ([segmentedControl selectedSegmentIndex]) {
		case 0:
			//NSLog(@"Segment 0");
			[wordsTableView reloadData];
			break;
		case 1:
			//NSLog(@"Segment 1");
			[wordsTableView reloadData];
			break;
		case UISegmentedControlNoSegment:
			break;
		default:
			NSLog(@"No option for: %d", [segmentedControl selectedSegmentIndex]);
	}
}


#pragma mark -
#pragma mark Memory management

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
    [super viewDidUnload];
}

- (void)dealloc {
    [super dealloc];
	
	//[segmentWordsSections release];
	//[segmentWords release];
}

@end

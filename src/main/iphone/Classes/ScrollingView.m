//
//  ScrollingView.m
//  Scrolling
//
//  Created by Alex Winston on 12/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "ScrollingView.h"


@implementation ScrollingView

#pragma mark -
#pragma mark Lifecycle methods

- (void)dealloc {
	[scrollView release];
    [super dealloc];
}

#pragma mark -
#pragma mark UIView methods

- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event {
	if (!self.userInteractionEnabled)
		return nil;
	
	UIView *child = [super hitTest:point withEvent:event];
	
	if (child == self) {
		return scrollView;
	} 
	
	return child; 
}

@end

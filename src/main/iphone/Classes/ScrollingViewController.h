//
//  ScrollingViewController.h
//  Scrolling
//
//  Created by David Janes on 09-09-25.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "ScrollingView.h"
#import "DetailViewController.h"

#import "WordvaderDatastore.h"

@interface ScrollingViewController : UIViewController<SKProductsRequestDelegate, SKPaymentTransactionObserver, UIScrollViewDelegate, UINavigationControllerDelegate, UIImagePickerControllerDelegate>
{
	WordvaderDatastore *wordvaderDatastore;
	
	NSMutableDictionary *purchaseCounts;
	NSMutableArray *purchaseButtonTitles;
	NSMutableArray *pageViews;
	
	DetailViewController *currentFlippedViewController;
	
	IBOutlet UIBarButtonItem *closeFlippedViewButton;
	UIImageView *currentFlippedImageView;
	
	IBOutlet UIBarButtonItem *pickImageButton;
	UIBarButtonItem *pickImageActivityButton;
	UIActivityIndicatorView *pickImageActivityIndicator;
	
	IBOutlet ScrollingView *scrollingView;
	IBOutlet UIScrollView *scrollView;
	IBOutlet UIPageControl *pageControl;
	IBOutlet UILabel *instructionsLabel;
	
    BOOL pageControlIsChangingPage;
}
@property (nonatomic, retain) IBOutlet UIBarButtonItem *closeFlippedViewButton;
@property (nonatomic, retain) IBOutlet UIBarButtonItem *pickImageButton;
@property (nonatomic, retain) IBOutlet ScrollingView *scrollingView;
@property (nonatomic, retain) IBOutlet UIView *scrollView;
@property (nonatomic, retain) IBOutlet UIPageControl* pageControl;
@property (nonatomic, retain) IBOutlet UILabel* instructionsLabel;

- (void)setupPage;
- (void)enableActivityIndicator;
- (void)disableActivityIndicator;
- (void)changePageWithNumber:(NSInteger)pageNumber;
- (void)insertPage:(UIImage *)image animated:(BOOL)animated;
- (void)removeSolutionAtIndex:(int)index;

- (IBAction)changePage:(id)sender;
- (IBAction)closeFlipped:(id)sender;

- (IBAction)pickImage:(id)sender;
- (UIImage *)imageFromUIView:(UIView *)theView;

- (void)updateNumberOfHints;

- (void)completeTransaction:(SKPaymentTransaction *)transaction;
- (void)failedTransaction:(SKPaymentTransaction *)transaction;

@end


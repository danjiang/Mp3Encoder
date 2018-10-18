//
//  ViewController.m
//  Mp3Encoder
//
//  Created by Dan Jiang on 2018/10/18.
//  Copyright © 2018 Dan Jiang. All rights reserved.
//

#import "ViewController.h"
#import "mp3_encoder.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (IBAction)encoding:(id)sender {
    NSLog(@"Start Encode...");
    NSString *pcmFile = [[NSBundle mainBundle] pathForResource:@"vocal" ofType:@"pcm"];
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsDirectory = [paths objectAtIndex:0];
    NSString *mp3File = [documentsDirectory stringByAppendingPathComponent:@"vocal.mp3"];
    int sampleRate = 44100;
    int channels = 2;
    int bitRate = 128 * 1024;
    Mp3Encoder *encoder = new Mp3Encoder();
    encoder->init([pcmFile cStringUsingEncoding:NSUTF8StringEncoding], [mp3File cStringUsingEncoding:NSUTF8StringEncoding], sampleRate, channels, bitRate);
    encoder->encode();
    encoder->destroy();
    delete encoder;
    NSLog(@"Encode Success");
}

@end

//
//  mp3_encoder.h
//  Mp3Encoder
//
//  Created by Dan Jiang on 2018/10/16.
//

#ifndef mp3_encoder_h
#define mp3_encoder_h

#include "lame/lame.h"

class Mp3Encoder {
private:
    FILE* pcmFile;
    FILE* mp3File;
    lame_t lameClient;
    
public:
    Mp3Encoder();
    ~Mp3Encoder();
    
    int init(const char* pcmFilePath, const char *mp3FilePath, int sampleRate, int channels, int bitRate);
    void encode();
    void destroy();    
};


#endif /* mp3_encoder_h */

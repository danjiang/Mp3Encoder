# iOS

1. Compile Third Party Library: Compile for Different iOS Architectures
2. Link Third Party Library: Build Settings > Header Search Paths, Build Settings > Other C Flags, Build Phases > Link Binary With Libraries
3. Use Third Party Library: Objective-C -> Objective-C++ -> C++

# Android

1. Compile Third Party Library: Compile for Different Android Architectures
2. Link Third Party Library: use ndk-build or CMake to link as static library 
3. Use Third Party Library: Java -> JNI -> C++, use ndk-build or CMake to comiple as shared library

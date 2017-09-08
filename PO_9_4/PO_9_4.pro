QT += core
QT -= gui

CONFIG += c++11
QMAKE_CXXFLAGS+= -fopenmp
QMAKE_LFLAGS +=  -fopenmp
LIBS += -fopenmp
TARGET = PO_9_4
CONFIG += console
CONFIG -= app_bundle


SOURCES += main.cpp

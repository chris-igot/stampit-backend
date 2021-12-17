#!/bin/bash

awk '{sub("href=\"/","href=\"")}1' ./build/index.html > ./build/temp.html
awk '{sub("href=\"/","href=\"")}1' ./build/temp.html > ./build/temp2.html
awk '{sub("href=\"/","href=\"")}1' ./build/temp2.html > ./build/temp.html
awk '{sub("href=\"/","href=\"")}1' ./build/temp.html > ./build/temp2.html
awk '{sub("href=\"static/","href=\"")}1' ./build/temp2.html > ./build/temp.html
awk '{sub("src=\"/static/","src=\"")}1' ./build/temp.html > ./build/temp2.html
awk '{sub("src=\"/static/","src=\"")}1' ./build/temp2.html > ./build/temp.html
awk '{sub("<!doctype html>","<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"%><!DOCTYPE html>")}1' ./build/temp.html > ./build/stampyReact.jsp

rm ./build/static/js/*.map
rm ./build/static/css/*.map

cp -r -f ./build/static/* ../../backend/stampy/src/main/resources/static
cp -f ./build/asset-manifest.json ../../backend/stampy/src/main/resources/static
cp -f ./build/favicon.ico ../../backend/stampy/src/main/resources/static
cp -f ./build/logo192.png ../../backend/stampy/src/main/resources/static
cp -f ./build/logo512.png ../../backend/stampy/src/main/resources/static
cp -f ./build/manifest.json ../../backend/stampy/src/main/resources/static
cp -f ./build/robots.txt ../../backend/stampy/src/main/resources/static

cp -f ./build/stampyReact.jsp ../../backend/stampy/src/main/webapp/WEB-INF

package org.jetbrains.skija;

import org.jetbrains.skija.impl.Managed;
import org.jetbrains.skija.impl.Native;
import org.jetbrains.skija.impl.Stats;

public class Region extends Managed {
    static { Library.load(); }
    
    public enum Op {
        DIFFERENCE,
        INTERSECT,
        UNION,
        XOR,
        REVERSE_DIFFERENCE,
        REPLACE
    }

    public Region() {
        super(_nMake(), _finalizerPtr);
        Stats.onNativeCall();
    }

    public boolean set(Region r) {
        Stats.onNativeCall();
        return _nSet(_ptr, Native.getPtr(r));
    }

    public boolean isEmpty() {
        Stats.onNativeCall();
        return _nIsEmpty(_ptr);
    }

    public boolean isRect() {
        Stats.onNativeCall();
        return _nIsRect(_ptr);
    }

    public boolean isComplex() {
        Stats.onNativeCall();
        return _nIsComplex(_ptr);
    }

    public IRect getBounds() {
        Stats.onNativeCall();
        return _nGetBounds(_ptr);
    }

    public int computeRegionComplexity() {
        Stats.onNativeCall();
        return _nComputeRegionComplexity(_ptr);
    }

    public boolean getBoundaryPath(Path p) {
        Stats.onNativeCall();
        return _nGetBoundaryPath(_ptr, Native.getPtr(p));
    }

    public boolean setEmpty() {
        Stats.onNativeCall();
        return _nSetEmpty(_ptr);
    }

    public boolean setRect(IRect rect) {
        Stats.onNativeCall();
        return _nSetRect(_ptr, rect._left, rect._top, rect._right, rect._bottom);
    }

    public boolean setRects(IRect[] rects) {
        int[] arr = new int[rects.length * 4];
        for (int i = 0; i < rects.length; ++i) {
            arr[i * 4]     = rects[i]._left;
            arr[i * 4 + 1] = rects[i]._top;
            arr[i * 4 + 2] = rects[i]._right;
            arr[i * 4 + 3] = rects[i]._bottom;
        }
        Stats.onNativeCall();
        return _nSetRects(_ptr, arr);
    }

    public boolean setRegion(Region r) {
        Stats.onNativeCall();
        return _nSetRegion(_ptr, Native.getPtr(r));
    }

    public boolean setPath(Path path, Region clip) {
        Stats.onNativeCall();
        return _nSetPath(_ptr, Native.getPtr(path), Native.getPtr(clip));
    }

    public boolean intersects(IRect rect) {
        Stats.onNativeCall();
        return _nIntersectsIRect(_ptr, rect._left, rect._top, rect._right, rect._bottom);
    }

    public boolean intersects(Region r) {
        Stats.onNativeCall();
        return _nIntersectsRegion(_ptr, Native.getPtr(r));
    }

    public boolean contains(int x, int y) {
        Stats.onNativeCall();
        return _nContainsIPoint(_ptr, x, y);
    }

    public boolean contains(IRect rect) {
        Stats.onNativeCall();
        return _nContainsIRect(_ptr, rect._left, rect._top, rect._right, rect._bottom);
    }

    public boolean contains(Region r) {
        Stats.onNativeCall();
        return _nContainsRegion(_ptr, Native.getPtr(r));
    }

    public boolean quickContains(IRect rect) {
        Stats.onNativeCall();
        return _nQuickContains(_ptr, rect._left, rect._top, rect._right, rect._bottom);
    }

    public boolean quickReject(IRect rect) {
        Stats.onNativeCall();
        return _nQuickRejectIRect(_ptr, rect._left, rect._top, rect._right, rect._bottom);
    }

    public boolean quickReject(Region r) {
        Stats.onNativeCall();
        return _nQuickRejectRegion(_ptr, Native.getPtr(r));
    }

    public void translate(int dx, int dy) {
        Stats.onNativeCall();
        _nTranslate(_ptr, dx, dy);
    }

    public boolean op(IRect rect, Op op) {
        Stats.onNativeCall();
        return _nOpIRect(_ptr, rect._left, rect._top, rect._right, rect._bottom, op.ordinal());
    }

    public boolean op(Region r, Op op) {
        Stats.onNativeCall();
        return _nOpRegion(_ptr, Native.getPtr(r), op.ordinal());
    }

    public boolean op(IRect rect, Region r, Op op) {
        Stats.onNativeCall();
        return _nOpIRectRegion(_ptr, rect._left, rect._top, rect._right, rect._bottom, Native.getPtr(r), op.ordinal());
    }

    public boolean op(Region r, IRect rect, Op op) {
        Stats.onNativeCall();
        return _nOpRegionIRect(_ptr, Native.getPtr(r), rect._left, rect._top, rect._right, rect._bottom, op.ordinal());
    }

    public boolean op(Region a, Region b, Op op) {
        Stats.onNativeCall();
        return _nOpRegionRegion(_ptr, Native.getPtr(a), Native.getPtr(b), op.ordinal());
    }

    public static final  long    _finalizerPtr = _nGetFinalizer();
    public static native long    _nMake();
    public static native long    _nGetFinalizer();
    public static native boolean _nSet(long ptr, long regoinPtr);
    public static native boolean _nIsEmpty(long ptr);
    public static native boolean _nIsRect(long ptr);
    public static native boolean _nIsComplex(long ptr);
    public static native IRect   _nGetBounds(long ptr);
    public static native int     _nComputeRegionComplexity(long ptr);
    public static native boolean _nGetBoundaryPath(long ptr, long pathPtr);
    public static native boolean _nSetEmpty(long ptr);
    public static native boolean _nSetRect(long ptr, int left, int top, int right, int bottom);
    public static native boolean _nSetRects(long ptr, int[] rects);
    public static native boolean _nSetRegion(long ptr, long regionPtr);
    public static native boolean _nSetPath(long ptr, long pathPtr, long regionPtr);
    public static native boolean _nIntersectsIRect(long ptr, int left, int top, int right, int bottom);
    public static native boolean _nIntersectsRegion(long ptr, long regionPtr);
    public static native boolean _nContainsIPoint(long ptr, int x, int y);
    public static native boolean _nContainsIRect(long ptr, int left, int top, int right, int bottom);
    public static native boolean _nContainsRegion(long ptr, long regionPtr);
    public static native boolean _nQuickContains(long ptr, int left, int top, int right, int bottom);
    public static native boolean _nQuickRejectIRect(long ptr, int left, int top, int right, int bottom);
    public static native boolean _nQuickRejectRegion(long ptr, long regionPtr);
    public static native void    _nTranslate(long ptr, int dx, int dy);
    public static native boolean _nOpIRect(long ptr, int left, int top, int right, int bottom, int op);
    public static native boolean _nOpRegion(long ptr, long regionPtr, int op);
    public static native boolean _nOpIRectRegion(long ptr, int left, int top, int right, int bottom, long regionPtr, int op);
    public static native boolean _nOpRegionIRect(long ptr, long regionPtr, int left, int top, int right, int bottom, int op);
    public static native boolean _nOpRegionRegion(long ptr, long regionPtrA, long regionPtrB, int op);
}
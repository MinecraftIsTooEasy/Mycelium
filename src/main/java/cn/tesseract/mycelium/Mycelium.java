package cn.tesseract.mycelium;

import net.xiaoyu233.fml.classloading.KnotClassDelegate;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class Mycelium implements IMixinConfigPlugin {
    private static final MixinTransformerDelegate delegate;

    static {
        try {
            ClassLoader knotClassLoader = Mycelium.class.getClassLoader();
            Method knotClassDelegateMethod = knotClassLoader.getClass().getDeclaredMethod("getDelegate");
            knotClassDelegateMethod.setAccessible(true);
            KnotClassDelegate<?> knotClassDelegate = (KnotClassDelegate<?>) knotClassDelegateMethod.invoke(knotClassLoader);
            Field mixinTransformerField = knotClassDelegate.getClass().getDeclaredField("mixinTransformer");
            mixinTransformerField.setAccessible(true);
            delegate = new MixinTransformerDelegate((MixinTransformer) mixinTransformerField.get(knotClassDelegate));
            mixinTransformerField.set(knotClassDelegate, delegate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerHookContainer(String className) {
        delegate.hook.registerHookContainer(className);
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
package cn.tesseract.mycelium;

import cn.tesseract.mycelium.asm.HookClassTransformer;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.IMixinTransformer;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.mixin.transformer.ext.IExtensionRegistry;
import org.spongepowered.asm.transformers.TreeTransformer;

import java.util.List;

public class MixinTransformerDelegate extends TreeTransformer implements IMixinTransformer {
    private final MixinTransformer instance;
    public final HookClassTransformer hook = new HookClassTransformer();

    public MixinTransformerDelegate(MixinTransformer instance) {
        this.instance = instance;
    }

    @Override
    public IExtensionRegistry getExtensions() {
        return instance.getExtensions();
    }

    @Override
    public void audit(MixinEnvironment environment) {
        instance.audit(environment);
    }

    @Override
    public boolean isDelegationExcluded() {
        return instance.isDelegationExcluded();
    }

    @Override
    public String getName() {
        return instance.getName();
    }

    @Override
    public List<String> reload(String mixinClass, ClassNode classNode) {
        return instance.reload(mixinClass, classNode);
    }

    @Override
    public boolean computeFramesForClass(MixinEnvironment environment, String name, ClassNode classNode) {
        return instance.computeFramesForClass(environment, name, classNode);
    }

    @Override
    public byte[] transformClassBytes(String name, String transformedName, byte[] basicClass) {
        basicClass = hook.transform(transformedName, basicClass);
        basicClass = instance.transformClassBytes(name, transformedName, basicClass);
        return basicClass;
    }

    @Override
    public byte[] transformClass(MixinEnvironment environment, String name, byte[] classBytes) {
        return instance.transformClass(environment, name, classBytes);
    }

    @Override
    public boolean transformClass(MixinEnvironment environment, String name, ClassNode classNode) {
        return instance.transformClass(environment, name, classNode);
    }

    @Override
    public byte[] generateClass(MixinEnvironment environment, String name) {
        return instance.generateClass(environment, name);
    }

    @Override
    public boolean generateClass(MixinEnvironment environment, String name, ClassNode classNode) {
        return instance.generateClass(environment, name, classNode);
    }
}

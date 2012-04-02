package src.main.groovy.org.jggug.javaonetokyo.bof.dsl

class ConfigParser {

    Properties parse(Closure dsl) {
        def root = new NodeBuilder().config(dsl)
        def props = new Properties()
        traverse(root.value(), props, [])
        return props
    }

    void traverse(nodes, props, stack) {
        nodes.each { node ->
            def newStack = stack + node.name()
            def value = node.value()
            if (value in List) {
                traverse(value, props, newStack)
            } else {
                def key = newStack.join(".")
                props[key] = value
            }
        }
    }

}

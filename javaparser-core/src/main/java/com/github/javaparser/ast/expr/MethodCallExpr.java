/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 * 
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License 
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.ast.expr;

import com.github.javaparser.Range;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

import static com.github.javaparser.ast.expr.NameExpr.*;
import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * @author Julio Vilmar Gesser
 */
public final class MethodCallExpr extends Expression implements 
        NodeWithTypeArguments<MethodCallExpr>,
        NodeWithArguments<MethodCallExpr> {

    // TODO nullable
    private Expression scope;

    // TODO nullable
    private NodeList<Type<?>> typeArguments;

    private NameExpr name;

    private NodeList<Expression> args;

    public MethodCallExpr() {
        this(Range.UNKNOWN,
                null,
                new NodeList<>(),
                new NameExpr(),
                new NodeList<>());
    }

    public MethodCallExpr(final Expression scope, final String name) {
        this(Range.UNKNOWN,
                scope,
                new NodeList<>(),
                name(name),
                new NodeList<>());
    }

    public MethodCallExpr(final Expression scope, final NameExpr name, final NodeList<Expression> args) {
        this(Range.UNKNOWN,
                scope,
                new NodeList<>(),
                name,
                args);
    }

	public MethodCallExpr(final Range range, final Expression scope, final NodeList<Type<?>> typeArguments, final NameExpr name, final NodeList<Expression> args) {
		super(range);
		setScope(scope);
		setTypeArguments(typeArguments);
		setNameExpr(name);
		setArgs(args);
	}

    @Override
    public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(final VoidVisitor<A> v, final A arg) {
        v.visit(this, arg);
    }

    @Override
    public NodeList<Expression> getArgs() {
        return args;
    }

    public String getName() {
        return name.getName();
    }

    public NameExpr getNameExpr() {
        return name;
    }

    public Expression getScope() {
        return scope;
    }

    @Override
	public MethodCallExpr setArgs(final NodeList<Expression> args) {
		this.args = assertNotNull(args);
		setAsParentNodeOf(this.args);
        return this;
	}

    public MethodCallExpr setName(final String name) {
        setNameExpr(new NameExpr(name));
        return this;
    }

    public MethodCallExpr setNameExpr(NameExpr name) {
        this.name = name;
        setAsParentNodeOf(this.name);
        return this;
    }

    public MethodCallExpr setScope(final Expression scope) {
        this.scope = scope;
        setAsParentNodeOf(this.scope);
        return this;
    }

    @Override
    public NodeList<Type<?>> getTypeArguments() {
        return typeArguments;
    }

    @Override
    public MethodCallExpr setTypeArguments(final NodeList<Type<?>> types) {
        this.typeArguments = types;
        setAsParentNodeOf(this.typeArguments);
        return this;
    }
}

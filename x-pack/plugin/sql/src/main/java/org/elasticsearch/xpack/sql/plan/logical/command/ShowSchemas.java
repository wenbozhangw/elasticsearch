/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */
package org.elasticsearch.xpack.sql.plan.logical.command;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.xpack.ql.expression.Attribute;
import org.elasticsearch.xpack.ql.expression.FieldAttribute;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.ql.type.KeywordEsField;
import org.elasticsearch.xpack.sql.session.Cursor.Page;
import org.elasticsearch.xpack.sql.session.Rows;
import org.elasticsearch.xpack.sql.session.SqlSession;

import java.util.List;

import static java.util.Collections.singletonList;

public class ShowSchemas extends Command {

    public ShowSchemas(Source source) {
        super(source);
    }

    @Override
    protected NodeInfo<ShowSchemas> info() {
        return NodeInfo.create(this);
    }

    @Override
    public List<Attribute> output() {
        return singletonList(new FieldAttribute(source(), "schema", new KeywordEsField("schema")));
    }

    @Override
    public void execute(SqlSession session, ActionListener<Page> listener) {
        listener.onResponse(Page.last(Rows.empty(output())));
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return true;
    }
}

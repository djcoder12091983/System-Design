package org.lld.behavioral.vistor.audit;

// this defines asset interface for all concrete assets
// that can be audited by the visitor
public interface CorporateAsset {
    // Entry point for double dispatch
    void accept(AssetVisitor visitor);
}

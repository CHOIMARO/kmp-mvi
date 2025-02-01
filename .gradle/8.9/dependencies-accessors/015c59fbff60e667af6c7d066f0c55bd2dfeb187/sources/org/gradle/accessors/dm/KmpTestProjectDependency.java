package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class KmpTestProjectDependency extends DelegatingProjectDependency {

    @Inject
    public KmpTestProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":androidApp"
     */
    public AndroidAppProjectDependency getAndroidApp() { return new AndroidAppProjectDependency(getFactory(), create(":androidApp")); }

    /**
     * Creates a project dependency on the project at path ":data"
     */
    public DataProjectDependency getData() { return new DataProjectDependency(getFactory(), create(":data")); }

    /**
     * Creates a project dependency on the project at path ":domain"
     */
    public DomainProjectDependency getDomain() { return new DomainProjectDependency(getFactory(), create(":domain")); }

    /**
     * Creates a project dependency on the project at path ":shared"
     */
    public SharedProjectDependency getShared() { return new SharedProjectDependency(getFactory(), create(":shared")); }

    /**
     * Creates a project dependency on the project at path ":ui"
     */
    public UiProjectDependency getUi() { return new UiProjectDependency(getFactory(), create(":ui")); }

}

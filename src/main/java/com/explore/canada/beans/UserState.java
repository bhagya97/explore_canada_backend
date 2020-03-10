package com.explore.canada.beans;

public enum UserState {
    ACTIVE
            {
                public String toString()
                {
                    return "Y";
                }
            },
    DISABLED
            {
                public String toString()
                {
                    return "N";
                }
            }
}
